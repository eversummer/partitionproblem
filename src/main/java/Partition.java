public class Partition {

    public static void main(String... args){
        int s[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        partition(s, 9, 3);
    }
    public static void partition(int s[], int n, int k)
    {
        int m[][] = new int[20][20]; /* DP table for values */
        int d[][] = new int[20][20]; /* DP table for dividers */
        int p[] = new int[20]; /* prefix sums array */
        int cost; /* test split cost */
        int i,j,x; /* counters */
        p[0] = 0; /* construct prefix sums */
        for (i=1; i<=n; i++) p[i]=p[i-1]+s[i];
        for (i=1; i<=n; i++) m[i][1] = p[i]; /* initialize boundaries */
        for (j=1; j<=k; j++) m[1][j] = s[1];
        for (i=2; i<=n; i++) /* evaluate main recurrence */
            for (j=2; j<=k; j++) {
                m[i][j] = Integer.MAX_VALUE;
                for (x=1; x<=(i-1); x++) {
                    cost = Math.max(m[x][j-1], p[i]-p[x]);
                    if (m[i][j] > cost) {
                        m[i][j] = cost;
                        d[i][j] = x;
                    }
                }
            }
        reconstruct_partition(s,d,n,k); /* print book partition */
    }

    public static void reconstruct_partition(int s[],int [][] d, int n, int k)
    {
        if (k==1)
            print_books(s,1,n);
        else {
            reconstruct_partition(s,d,d[n][k],k-1);
            print_books(s,d[n][k]+1,n);
        }
    }
    public static void print_books(int s[], int start, int end)
    {
        int i; /* counter */
        for (i=start; i<=end; i++) System.out.println(s[i]);
        System.out.println("\n");
    }
}
