class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        int num1 = 0;
        int num2 = 0;
        for (Integer i : list1) {
            if (i == elem) {
                num1++;
            }
        }
            for (Integer j : list2) {
                if (j == elem) {
                    num2++;
            }
        }
            if (num1 == num2) {
                return true;
            }
        return false;
    }
}