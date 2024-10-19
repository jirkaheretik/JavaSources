package cz.braza.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class L2530MaximalScore {
    public static long maxKelements(int[] nums, int k) {
        Arrays.sort(nums);
        long score = 0;
        int length = nums.length - 1;
        int skipped = 0;
        for (int run = 0; run < k; run++) {
            int top = length - skipped;
            score += nums[top];
            int third = nums[top] / 3;
            if (3 * third < nums[top])
                third++;
            nums[top] = third;

            // need to bubble at all or just drop the number?
            if (nums[Math.max(0, top - k - 1)] > third)
                skipped++;
            else
            // bubble into place:
                while (top > 0 && nums[top] < nums[top - 1]) {
                    int dummy = nums[top];
                    nums[top] = nums[top - 1];
                    nums[top - 1] = dummy;
                    top--;
                    if (top == 0 || top + k - run + 1 < nums.length)
                        break; // no need to swap again
                }
        }
        return score;
    }

    public static long maxKelementsZeSolutions(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int x : nums)
            pq.offer(x);
        long score = 0;
        while(!pq.isEmpty() && k-- > 0) {
            int val = pq.poll();
            score += val;
            pq.add((int) Math.ceil(val / 3.0));
        }
        return score;
    }

    public static void main(String[] args) {
        int[] nums = {1, 10, 3, 3, 3};
        int[] test = {967634272,306305177,105667158,246514734,216748107,
        837748123,91127998,574533111,952383281,119241286,859265636,657278776,
        993945055,631366099,357227019,511700415,641499150,315341223,546520666,
                419300279,32099258,836075008,78513556,14492424,686302346,170677276,
        389688408,187117498,863314714,612983391,569358448,226390010,197676785,
        225079861,746199924,949470415,498893946,898409466,167769033,197600779,
        9907626,951521060,39228861,214329202,879178512,615290757,488758753,694674878,
        433466041,894973271,364181679,34095763,467802869,308915082,399305428,510810218,
        383378161,159883494,964643245,798163487,344047989,689292772,615362965,593165996,
        389967884,152518526,949920661,257253468,613285716,593013793,741269310,707853647,
        66339,459894178,676038360,473379109,604821640,748550377,649858716,962548253,784833751,
        929040405,944042775,625961814,566293714,501682048,236614868,986705897,838407658,678660890,
        532937706,374961786,778135709,263273359,408682444,362754710,252330034,735731042,372869538,
        920141261,795805661,326641927,572831264,331287372,710269122,435931624,976573796,338198541,
        826626203,223364448,278745883,987257867,674639449,975653265,139574933,841738652,610639122,
        595668581,290154590,797988969,761972994,969363083,292543902,172665202,910248112,76024412,
        74375730,221286001,289624553,671673381,610951381,417468066,516554559,148960058,478450361,
        643799240,487682501,621107928,909859362,251938111,23017839,899405500,494314846,13440168,
        163050676,208441708,842402252,34708534,524313875,376550293,561865802,953941897,616353525,
        710992272,558087498,628312414,111282727,382280981,599499812,584276880,428096480,608157405,
        205395062,459148269,313892360,754939695,874592404,37192621,263784814,657454359,519804916,
        894329037,939060708,349129024,284388263,2810106,477048171,671251055,207767547,546211342,
        287270452,631011302,373572523,288315673,730891055,75935085,226161644,713575414,524041436,
        444188340,183329729,208925055,810810260,689686890,221542228,787541727,472571966,668047858,
        510976005,374427412,470312530,536915249,862593804,938648208,202042251,235713906,410779512,
        795281781,889886154,927010419,394743724,148977276,686845703,306933781,783973321,105519132,
        687329235,501648967,921415757,436852460,118540946,829510103,180342145,426103622,732429643,
        460685216,521543359,657147179,953865873,727740441,216139346,383858420,50078755,795341428,
        510136178,999881026,649606542,658289840,578736846,296634488,829921781,494764914,574634029,
        421385489,693835537,770043074,921474619,787883190,739061662,116077616,196783528,820228062,
        666245526,182168864,865308678,234400643,454507163,293200729,593276185,576458364,599029823,
        778960446,654808970,433599713,148585120,858716060,383011452,898198845,204450111,155548375,
        637201044,171545037,371557036,68605287,403283514,616268619,848589398,434555991,387560148,
        160300074,957342522,411005522,290998564,716400355,748381435,131183056,716202524,89861076,
        346401164,127077427,617545289,806794407,321646723,721867399,640854995,490374686,350661164,
        962160652,638441244,747780816,306082077,12900124,722364594,665017778,162033834,410733735,
        347373834,339473867,940985776,724074671,187651509,219344788,60970257,764094844,39439930,
        356516328,681652636,866395979,751664978,676205335,334389592,803717136,109778375,115439060,
        253440082,927998070,71955491,391295262,517448200,476370283,687684718,814856078,959375721,
        958905114,141762596,681829242,123461328,800722985,480982873,486907794,724646229,402999885,
        864735997,5001785,726076437,410572787,619878928,528612531,719461557,698954011,757871965};
        System.out.println("MKS: " + maxKelementsZeSolutions(nums, 3));
        System.out.println(maxKelements(nums, 3));
        System.out.println(test.length);
        System.out.println("MKS: " + maxKelementsZeSolutions(test, 100000));
        System.out.println(maxKelements(test, 100000));
        int[] test0 = {10, 10, 10, 10, 10};
        System.out.println("MKS: " + maxKelementsZeSolutions(test0, 5));
    }
}
