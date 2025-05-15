package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.ForagingTreeSeedsType;

public class TreeSeed extends Item{

    private ForagingTreeSeedsType treeSeedsType;

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

    public TreeSeed(ForagingTreeSeedsType treeSeedsType) {
        super(Quality.DEFAULT, Integer.MAX_VALUE, treeSeedsType.value, 0, treeSeedsType.name);
        this.treeSeedsType = treeSeedsType;
    }

    public ForagingTreeSeedsType getTreeSeedsType() {
        return treeSeedsType;
    }

    public void setTreeSeedsType(ForagingTreeSeedsType treeSeedsType) {
        this.treeSeedsType = treeSeedsType;
    }
}
