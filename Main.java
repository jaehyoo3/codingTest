public class Main {
    public static void main(String[] args) {
        int sum = 4;
        int[] coins = {1, 2, 3};
        int result = countCoinCombinations(sum, coins);

        System.out.println(result);
    }

    public static int countCoinCombinations(int sum, int[] coins) {
        int[] dp = new int[sum+1];
        dp[0] = 1; // 0원을 만드는 방법은 1가지 (아무 동전도 사용하지 않는 경우)
        for(int coin : coins) {
            for(int i=coin; i<=sum; i++) {
                dp[i] += dp[i-coin]; // i원을 만드는 방법은 (i-coin)원을 만드는 방법에 coin원 동전을 추가하는 경우와 같음
            }
        }
        return dp[sum];
    }
}
