package datastructure.binarySearch;

public class BinarySearch {
    public static int search(int[] array,int fromIndex, int toIndex,int key) {
        int begin = fromIndex;
        int end = toIndex;
        while(begin <= end) {
            int mid = (begin + end) >>> 1;
            int midVal = array[mid];
            if(midVal < key) {
                begin = mid + 1;
            }else if(midVal > key){
                end = mid - 1;
            }else {
                return mid;
            }
        }
        return -(fromIndex + 1);
    }
    
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int search = search(array, 0, 19, 21);
        System.out.println(search);
    }
}
