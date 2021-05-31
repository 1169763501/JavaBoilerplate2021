package com.nbnfsoft.admin.utils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.DefaultNodeParser;
import cn.hutool.core.lang.tree.parser.NodeParser;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 9:28 2020-12-17
 */
public class TreeUtilExtend extends TreeUtil {

    public static <E> List<Tree<E>> build(List<TreeNode<E>> list, E parentId, TreeNodeConfig treeNodeConfig) {
        return build(list, parentId, treeNodeConfig, new DefaultNodeParser());
    }

    public static <T, E> List<Tree<E>> build(List<T> list, E parentId, TreeNodeConfig treeNodeConfig, NodeParser<T, E> nodeParser) {
        List<Tree<E>> treeList = CollUtil.newArrayList(new Tree[0]);
        Iterator var6 = list.iterator();

        while (var6.hasNext()) {
            T obj = (T) var6.next();
            Tree<E> tree = new Tree(treeNodeConfig);
            nodeParser.parse(obj, tree);
            treeList.add(tree);
        }

        List<Tree<E>> finalTreeList = CollUtil.newArrayList(new Tree[0]);
        Iterator var11 = treeList.iterator();

        while (var11.hasNext()) {
            Tree<E> node = (Tree) var11.next();
            if (parentId.equals(node.getParentId())) {
                finalTreeList.add(node);
                innerBuild(treeList, node, 0, treeNodeConfig.getDeep());
            }
        }
        finalTreeList = finalTreeList.stream().sorted().collect(Collectors.toList());
        return finalTreeList;
    }

    private static <T> void innerBuild(List<Tree<T>> treeNodes, Tree<T> parentNode, int deep, Integer maxDeep) {
        if (!CollUtil.isEmpty(treeNodes)) {
            if (maxDeep == null || deep < maxDeep.intValue()) {
                treeNodes = treeNodes.stream().sorted().collect(Collectors.toList());
                Iterator var4 = treeNodes.iterator();

                while (var4.hasNext()) {
                    Tree<T> childNode = (Tree) var4.next();
                    if (parentNode.getId().equals(childNode.getParentId())) {
                        List<Tree<T>> children = parentNode.getChildren();
                        if (children == null) {
                            children = CollUtil.newArrayList(new Tree[0]);
                            parentNode.setChildren(children);
                        }
                        (children).add(childNode);
                        childNode.setParent(parentNode);
                        innerBuild(treeNodes, childNode, deep + 1, maxDeep);
                    }
                }
                if (parentNode.getChildren() == null || parentNode.getChildren().isEmpty()) {
                    parentNode.setChildren(CollUtil.newArrayList(new Tree[0]));
                }
            }
        }
    }

}
