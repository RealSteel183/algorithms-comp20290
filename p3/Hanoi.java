package p3;

public class Hanoi {
    /**
     * Moves a given number of disks from source to destination.
     * 
     * @param n number of disks to move from source to destination
     * @param src the source tower
     * @param dest the destination tower
     * @param aux the auxiliary tower to help move the disk
     */
    public static void towersOfHanoi(int n, String src, String aux, String dest) {
        if (n == 1) {
            System.out.println("Move disk from " + src + " to " + dest);
        } else {
            towersOfHanoi(n - 1, src, dest, aux);
            System.out.println("Move disk from " + src + " to " + dest);
            towersOfHanoi(n - 1, aux, src, dest);
        }
    }
    public static void main(String[] args) {
        int n = 3;
        towersOfHanoi(n, "SOURCE","AUXILIARY", "DESTINATION");
        System.out.println("Number of moves: " + (int)(Math.pow(2, n) - 1));
    }
}
