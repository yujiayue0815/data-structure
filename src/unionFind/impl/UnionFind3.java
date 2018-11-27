package unionFind.impl;

import unionFind.UF;

public class UnionFind3 implements UF {

    private int[] sz; //用来记录每棵树的节点的个数，从而保证再联合的时候保证树的深度
    private int[] parent;


    public UnionFind3(int size) {
        this.parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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
        if (sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
