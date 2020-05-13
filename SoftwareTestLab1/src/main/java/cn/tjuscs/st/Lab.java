package cn.tjuscs.st;

public class Lab {
    public Lab() {
    }

    private static final int[] myMoney = {50, 20, 10, 5, 5, 1, 1, 1};

    public boolean verify(int number) {
        for (int value : myMoney) {
            number = number >= value ? number - value : number;
        }
        return number == 0;
    }
}
