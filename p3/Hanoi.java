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
    public static void towersOfHanoi(int n, String src, String dest, String aux) {
        if (n == 1) {
            System.out.println("Move disk from " + src + " to " + dest);
        } else {
            towersOfHanoi(n - 1, src, aux, dest);
            System.out.println("Move disk from " + src + " to " + dest);
            towersOfHanoi(n - 1, aux, dest, src);
        }
    }
    
    public static void main(String[] args) {
        int n = 3;
        towersOfHanoi(n, "SOURCE", "DESTINATION", "AUXILIARY");
        System.out.println("Number of moves: " + (int)(Math.pow(2, n) - 1));
    }
}
