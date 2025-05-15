package Models.Maps;

import java.util.ArrayList;

public class Map {
    private ArrayList<Farm> farms;
    private Village village;
    private int width;  // Total width of the map
    private int height; // Total height of the map

    private Map(ArrayList<Farm> farms, Village village, int width, int height) {
        this.farms = farms;
        this.village = village;
        this.width = width;
        this.height = height;
    }

    public static Map makeMap() {
        ArrayList<Farm> farms = new ArrayList<>();
        Village village = new Village();

        // Create 4 farms (one for each corner)
        for (int i = 0; i < 4; i++) {
            Farm farm = new Farm(new ArrayList<>(), new ArrayList<>());
            farm.setFarmNumber(i + 1);
            farms.add(farm);
        }

        // Initialize map with default size (could be configurable)
        return new Map(farms, village, 100, 100);
    }

    public Cells findCell(int x, int y) {
        // Check village first
        Cells cell = village.findCellVillage(x, y);
        if (cell != null) return cell;

        // Check farms
        for (Farm farm : farms) {
            cell = farm.findCellFarm(x, y);
            if (cell != null) return cell;
        }

        // If not found, return a default walkable cell
        return new Cells(new Position(x, y), null);
    }

    // Print a square area of the map around a position
    public void printMapArea(int x, int y, int size) {
        int startX = Math.max(0,x - size/2);
        int startY = Math.max(0, y - size/2);
        int endX = Math.min(width, x + size/2);
        int endY = Math.min(height, y + size/2);

        for ( y = startY; y <= endY; y++) {
            for ( x = startX; x <= endX; x++) {
                Cells cell = findCell(x, y);
                if (cell.getObjectOnCell() != null) {
                    System.out.print(cell.getObjectOnCell().toString() + " ");
                } else {
                    System.out.print(". "); // Empty cell
                }
            }
            System.out.println();
        }
    }

    public void helpReadingMap() {
        System.out.println("Map Legend:");
        System.out.println(".  - Empty space");
        System.out.println("T  - Tree");
        System.out.println("C  - Crop");
        System.out.println("B  - Building");
        System.out.println("H  - House");
        System.out.println("S  - Store");
        System.out.println("A  - Animal");
        System.out.println("L  - Lake/Water");
        System.out.println("D  - Dropped item");
        System.out.println("X  - Burnt cell");
        // Add more as needed
    }

    public void addFarm(Farm farm) {
        farms.add(farm);
    }

    public ArrayList<Farm> getFarms() {
        return farms;
    }

    public Village getVillage() {
        return village;
    }
}
