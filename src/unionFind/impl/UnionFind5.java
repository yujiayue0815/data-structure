package unionFind.impl;

import unionFind.UF;

/**
 * 路径压缩
 */
public class UnionFind5 implements UF {

    private int[] rank; //用来记录每棵子树的节点的深度（此时只能代表排名，不能直观代表深度）
    private int[] parent;


    public UnionFind5(int size) {
        this.parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
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
        {
            parent[q] = parent[parent[q]]; //采用路径压缩的方式降低树的深度
            q = parent[q];
        }
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
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] < rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
