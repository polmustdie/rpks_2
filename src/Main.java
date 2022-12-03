import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static boolean foundExchange = false;
    static int[] availableNominals;
    public static String mainExchange(int moneyToExcange, int[] _availableNominals) {
        if (moneyToExcange <= 0 || _availableNominals == null || _availableNominals.length == 0) {
            return "Can't exchange money";
        }

        availableNominals = _availableNominals;
        ArrayList<Integer> listNominal = new ArrayList<>();
        recursiveExchange(moneyToExcange, listNominal);
        if (listNominal.size() == 0) {
            return "Can't exchange money";
        }

        Map<Integer, Integer> nominalAndAmount = countNominal(listNominal);
        //ArrayList<Integer> nominalAndAmount = countNoms(listNominal);

        return toStringConversion(nominalAndAmount, moneyToExcange);
    }


    private static void recursiveExchange(int currMoney, ArrayList<Integer> currNominal) {
        for (int nominalValue : availableNominals)
        {
            if (foundExchange) return;

            if (currMoney == 0) {
                foundExchange = true;
            }  else if (currMoney - nominalValue >= 0) {
                currMoney -= nominalValue;
                currNominal.add(nominalValue);

                recursiveExchange(currMoney, currNominal);

                if (!foundExchange) {
                    currMoney += nominalValue;
                    currNominal.remove(currNominal.size() - 1);
                }
            }
        }
    }
//    public static ArrayList<Integer> countNoms(List<Integer> listNominal) {
//        ArrayList<Integer> nominalAndItsNumber = new ArrayList<>();
//        ArrayList<Integer> listNominalCopy = new ArrayList<>();
//        for (int i = 0; i < listNominal.size(); i++) {
//            listNominalCopy.add(listNominal.get(i));
//        }
//        for (Integer nominal : listNominal) {
//            if (nominalAndItsNumber.contains(nominal)) {
//                nominalAndItsNumber.add(listNominal.indexOf(nominal), 1);
//            }
//            else {
//                nominalAndItsNumber.add(listNominal.indexOf(nominal), nominalAndItsNumber.get(listNominal.indexOf(nominal))+1);
//            }
//        }
//        return nominalAndItsNumber;
//    }

    public static Map<Integer, Integer> countNominal(List<Integer> listNominal) {
        HashMap<Integer, Integer> nominalAndAmount = new HashMap<>();
        for (Integer nominal : listNominal) {
            if (!nominalAndAmount.containsKey(nominal)) {
                nominalAndAmount.put(nominal, 1);
            } else {
                nominalAndAmount.put(nominal, nominalAndAmount.get(nominal)+1);
            }
        }
        return nominalAndAmount;
    }

    public static String toStringConversion(Map<Integer, Integer> nominalAndAmount, int money) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(money);
        stringBuilder.append("->");
        for (Integer nominal : nominalAndAmount.keySet()) {
            stringBuilder.append(nominal);
            stringBuilder.append('[');
            stringBuilder.append(nominalAndAmount.get(nominal));
            stringBuilder.append(']');
            stringBuilder.append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        System.out.println(mainExchange(13, new int[]{5, 7}));
    }
}
