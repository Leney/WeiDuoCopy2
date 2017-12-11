/*
 * Copyright 2016 - 2017 ShineM (Xinyuan)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under.
 */

package com.zhongke.weiduo.mvp.ui.widget.tree.treeview;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点对象
 * Created by llj on 2017/4/20.
 */

public class TreeNode {
    private int level;

    private Object value;

    private TreeNode parent;

    private List<TreeNode> children;

    private int index;

    private boolean expanded;

    private boolean selected;

    private boolean itemClickEnable = true;

    /** 体系*/
    public static final int TYPE_SYSTEM = 0;
    /** 规划*/
    public static final int TYPE_SCHEME = 1;
    /** 目标*/
    public static final int TYPE_TARGET = 2;
    /** 计划*/
    public static final int TYPE_PLAN = 3;
    /** 活动*/
    public static final int TYPE_ACTIVE = 4;
    /** 节点类型*/
    private int type = -1;
    /** 类型id*/
    private int valueId;
    /** 所属类型的id*/
    private int belong;
    /** 所属第一级的指示点的宽度*/
    private int firstWidth;
    /** item前显示的icon资源地址*/
    private int iconRes = -1;
    private int bookId;
    private int resId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public TreeNode(Object value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static TreeNode root() {
        TreeNode treeNode = new TreeNode(null);
        return treeNode;
    }

    public void addChild(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        children.add(treeNode);
        treeNode.setIndex(getChildren().size());
        treeNode.setParent(this);
    }


    public void removeChild(TreeNode treeNode) {
        if (treeNode == null || getChildren().size() < 1) {
            return;
        }
        if (getChildren().indexOf(treeNode) != -1) {
            getChildren().remove(treeNode);
        }
    }

    public boolean isLastChild() {
        if (parent == null) {
            return false;
        }
        List<TreeNode> children = parent.getChildren();
        return children.size() > 0 && children.indexOf(this) == children.size() - 1;
    }

    public int getResId() {
        return resId;
    }

    public TreeNode setResId(int resId) {
        this.resId = resId;
        return this;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public boolean hasChild() {
        return children.size() > 0;
    }

    public boolean isItemClickEnable() {
        return itemClickEnable;
    }

    public void setItemClickEnable(boolean itemClickEnable) {
        this.itemClickEnable = itemClickEnable;
    }

    public String getId() {
        return getLevel() + "," + getIndex();
    }

    private int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getType() {
        return type;
    }

    public TreeNode setType(int type) {
        this.type = type;
        return this;
    }

    public int getValueId() {
        return valueId;
    }

    public TreeNode setValueId(int valueId) {
        this.valueId = valueId;
        return this;
    }

    public int getBelong() {
        return belong;
    }

    public TreeNode setBelong(int belong) {
        this.belong = belong;
        return this;
    }

    public int getFirstWidth() {
        return firstWidth;
    }

    public TreeNode setFirstWidth(int firstWidth) {
        this.firstWidth = firstWidth;
        return this;
    }

    public int getIconRes() {
        return iconRes;
    }

    public TreeNode setIconRes(int iconRes) {
        this.iconRes = iconRes;
        return this;
    }
}
