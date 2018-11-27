package unionFind.impl;

import unionFind.UF;

/**
 * 此种实现方式是采用森林树的方式实现，当每个节点在初始化的时候指向的是自身，此时每个都是一个独立的树，
 * 在查询两个节点是否是连接的时候的时间复杂度是O(h) --h 代表的是树的高度，如果将及两个节点相连接只需要将两个节点
 * 的根节点相连接，将两个节点相连接的时间复杂度是O(h)级别的，两个操作都必须和两个节点所在的树的深度有很大的的关系。
 *
 * 该方式是实现并查集的常用方式
 */
public class UnionFind2 implements UF {
    private int[] parent;


    public UnionFind2(int size) {
        this.parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int q) {
        if (q < 0 || q >= parent.length)
            throw new IllegalArgumentException("下标不合法");
        while (q != parent[q]) //当节点的指向自身的时候证明，该节点是根节点
            q = parent[q];
        return q;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        //当p节点的根节点和q节点的根节点不想等时，将p节点的根节点指向q的根节点
        parent[p] = qRoot;
    }
}
