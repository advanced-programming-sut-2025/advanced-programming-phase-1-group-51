package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.TreeSeedsType;

public class TreeSeed extends Item{

    private TreeSeedsType treeSeedsType;

    public TreeSeed() {
    }

    @Override
    public void useItem() {

    }

    @Override
    public void deleteItem() {

    }

    @Override
    public void dropItem() {

    }

    public TreeSeed(TreeSeedsType treeSeedsType) {
        super(Quality.DEFAULT, Integer.MAX_VALUE, treeSeedsType.value, 0, treeSeedsType.name);
        this.treeSeedsType = treeSeedsType;
    }

    public TreeSeedsType getTreeSeedsType() {
        return treeSeedsType;
    }

    public void setTreeSeedsType(TreeSeedsType treeSeedsType) {
        this.treeSeedsType = treeSeedsType;
    }
}
