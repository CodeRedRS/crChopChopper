package crChopChopper.var.enums;

/**
 * Created by CodeRed on 10/26/2014.
 */
public enum Tree {
    NONE("None", -1),
    TREE("Tree", 1282, 1289, 38760, 38782, 38783, 38785, 47598, 9354, 9355, 9366, 9387, 9388, 47600),
    ACHEY("Achey", -1),
    OAK("Oak", 38731, 3037, 10083, 38731, 38732),
    WILLOW("Willow", 38627, 38616, 58006),
    TEAK("Teak", -1),
    MAPLE("Maple", -1),
    HOLLOW("Hollow", -1),
    MAHOGANY("Mahogany", -1),
    ARCTIC("Arctic", -1),
    EUCALYPTUS("Eucalyptus", -1),
    YEW("Yew", 38755),
    IVY("Ivy", -1),
    MAGIC("Magic", -1),
    ELDER("Elder", -1);
    private final String treeName;
    private final int[] treeId;

    private Tree(final String treeName, final int... treeID) {
        this.treeName = treeName;
        this.treeId = treeID;
    }

    String getName() {
        return this.treeName;
    }

    public int[] getId() {
        return this.treeId;
    }

    @Override
    public String toString() {
        return getName();
    }
}
