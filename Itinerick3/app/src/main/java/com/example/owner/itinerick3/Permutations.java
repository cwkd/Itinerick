package myalgorithm;

/**
 * Created by John on 4/12/2017.
 */

import java.lang.reflect.Array;
import java.util.*;
class Permutations<E> implements  Iterator<E[]>{

    private E[] arr;
    private int[] ind;
    private boolean has_next;

    public E[] output;//next() returns this array, make it public

    Permutations(E[] arr){
        this.arr = arr.clone();
        ind = new int[arr.length];
        //convert an array of any elements into array of integers - first occurrence is used to enumerate
        Map<E, Integer> hm = new HashMap<E, Integer>();
        for(int i = 0; i < arr.length; i++){
            Integer n = hm.get(arr[i]);
            if (n == null){
                hm.put(arr[i], i);
                n = i;
            }
            ind[i] = n.intValue();
        }
        Arrays.sort(ind);//start with ascending sequence of integers


        //output = new E[arr.length]; <-- cannot do in Java with generics, so use reflection
        output = (E[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        has_next = true;
    }

    public boolean hasNext() {
        return has_next;
    }

    /**
     * Computes next permutations. Same array instance is returned every time!
     * @return
     */
    public E[] next() {
        if (!has_next)
            throw new NoSuchElementException();

        for(int i = 0; i < ind.length; i++){
            output[i] = arr[ind[i]];
        }


        //get next permutation
        has_next = false;
        for(int tail = ind.length - 1;tail > 0;tail--){
            if (ind[tail - 1] < ind[tail]){//still increasing

                //find last element which does not exceed ind[tail-1]
                int s = ind.length - 1;
                while(ind[tail-1] >= ind[s])
                    s--;

                swap(ind, tail-1, s);

                //reverse order of elements in the tail
                for(int i = tail, j = ind.length - 1; i < j; i++, j--){
                    swap(ind, i, j);
                }
                has_next = true;
                break;
            }

        }
        return output;
    }

    private void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public List<Vertex[]> createPermutedVertexListList(Vertex[] vertices){

        Permutations<Vertex> perm = new Permutations<Vertex>(vertices);
        List<Vertex[]> permutedVertexListList = new ArrayList<>();
        while(perm.hasNext()){
            permutedVertexListList.add(perm.next());
        }
        return permutedVertexListList;
    }

    public void remove() {
    }

    public static void main(String[] args) {
        Vertex v0 = new Vertex("Buddha Tooth Temple");
        Vertex v1 = new Vertex("Gardens By the Bay");
        Vertex v2 = new Vertex("Haw Par Villa");
        Vertex v3 = new Vertex("Marina Bay Sands");
        Vertex v4 = new Vertex("Merlion");
        Vertex v5 = new Vertex("Mustafa Shopping Centre");
        Vertex v6 = new Vertex("Singapore Zoo");
        Vertex v7 = new Vertex("Little India");
        Vertex v8 = new Vertex("Universal Studios Singapore");
        v0.adjacencies = new Edge[]{new Edge(v1, 25, 1.40, "Public Transport"), new Edge(v2, 22, 3.40, "Public Transport"), new Edge(v3, 19, 1.70, "Public Transport"),
                new Edge(v4, 14, 1.10, "Public Transport"), new Edge(v5, 24, 1.40, "Public Transport"), new Edge(v6, 86, 4.70, "Public Transport"),
                new Edge(v7, 22, 2, "Public Transport"), new Edge(v8, 22, 2.10, "Public Transport"), new Edge(v1, 11, 11, "Taxi"), new Edge(v2, 14, 11.50, "Taxi"),
                new Edge(v3, 10, 10.50, "Taxi"), new Edge(v4, 11, 8, "Taxi"), new Edge(v5, 17, 18.20, "Taxi"), new Edge(v6, 27, 28, "Taxi"), new Edge(v7, 15, 15, "Taxi"),
                new Edge(v8, 13, 12.60, "Taxi"), new Edge(v1, 32, 0, "Foot"), new Edge(v2, 107, 0, "Foot"), new Edge(v3, 12, 0, "Foot"), new Edge(v4, 12, 0, "Foot"),
                new Edge(v5, 50, 0, "Foot"), new Edge(v6, 288, 0, "Foot"), new Edge(v7, 42, 0, "Foot"), new Edge(v8, 59, 0, "Foot")};

        v1.adjacencies = new Edge[]{new Edge(v0, 25, 1.40, "Public Transport"), new Edge(v2, 37, 3.70, "Public Transport"), new Edge(v3, 10, 0, "Public Transport"),
                new Edge(v4, 14, 1.10, "Public Transport"), new Edge(v5, 24, 1.50, "Public Transport"), new Edge(v6, 79, 5.10, "Public Transport"),
                new Edge(v7, 20, 2.10, "Public Transport"), new Edge(v8, 38, 2.20, "Public Transport"), new Edge(v0, 13, 11.60, "Taxi"), new Edge(v2, 15, 16.50, "Taxi"),
                new Edge(v3, 8, 5.60, "Taxi"), new Edge(v4, 14, 13.10, "Taxi"), new Edge(v5, 23, 17.70, "Taxi"), new Edge(v6, 32, 23.20, "Taxi"), new Edge(v7, 17, 12.40, "Taxi"),
                new Edge(v8, 15, 8.60, "Taxi"), new Edge(v0, 32, 0, "Foot"), new Edge(v2, 135, 0, "Foot"), new Edge(v3, 11, 0, "Foot"), new Edge(v4, 16, 0, "Foot"),
                new Edge(v5, 41, 0, "Foot"), new Edge(v6, 344, 0, "Foot"), new Edge(v7, 37, 0, "Foot"), new Edge(v8, 86, 0, "Foot")};

        v2.adjacencies = new Edge[]{new Edge(v0, 22, 3.40, "Public Transport"), new Edge(v1, 37, 3.70, "Public Transport"), new Edge(v3, 27, 2.20, "Public Transport"),
                new Edge(v4, 28, 2.90, "Public Transport"), new Edge(v5, 39, 2.00, "Public Transport"), new Edge(v6, 75, 4.50, "Public Transport"),
                new Edge(v7, 35, 2, "Public Transport"), new Edge(v8, 24, 1.20, "Public Transport"), new Edge(v0, 16, 12.20, "Taxi"), new Edge(v1, 12, 15.00, "Taxi"),
                new Edge(v3, 13, 10.70, "Taxi"), new Edge(v4, 14, 14.40, "Taxi"), new Edge(v5, 23, 17.70, "Taxi"), new Edge(v6, 23, 22, "Taxi"), new Edge(v7, 20, 20.70, "Taxi"),
                new Edge(v8, 17, 17.70, "Taxi"), new Edge(v0, 107, 0, "Foot"), new Edge(v1, 135, 0, "Foot"), new Edge(v3, 133, 0, "Foot"), new Edge(v4, 123, 0, "Foot"),
                new Edge(v5, 145, 0, "Foot"), new Edge(v6, 264, 0, "Foot"), new Edge(v7, 137, 0, "Foot"), new Edge(v8, 81, 0, "Foot")};

        v3.adjacencies = new Edge[]{new Edge(v0, 19, 1.70, "Public Transport"), new Edge(v1, 10, 0.00, "Public Transport"), new Edge(v2, 27, 2.20, "Public Transport"),
                new Edge(v4, 7, 0.81, "Public Transport"), new Edge(v5, 19, 1.34, "Public Transport"), new Edge(v6, 52, 3.10, "Public Transport"),
                new Edge(v7, 14, 0.91, "Public Transport"), new Edge(v8, 26, 1.70, "Public Transport"), new Edge(v0, 5, 7, "Taxi"), new Edge(v1, 9, 9, "Taxi"),
                new Edge(v2, 17, 15.50, "Taxi"), new Edge(v4, 8, 5.30, "Taxi"), new Edge(v5, 17, 18.20, "Taxi"), new Edge(v6, 28, 26.10, "Taxi"), new Edge(v7, 17, 12.40, "Taxi"),
                new Edge(v8, 20, 19.40, "Taxi"), new Edge(v0, 12, 0, "Foot"), new Edge(v1, 11, 0, "Foot"), new Edge(v2, 133, 0, "Foot"), new Edge(v4, 19, 0, "Foot"),
                new Edge(v5, 44, 0, "Foot"), new Edge(v6, 285, 0, "Foot"), new Edge(v7, 39, 0, "Foot"), new Edge(v8, 83, 0, "Foot")};

        v4.adjacencies = new Edge[]{new Edge(v0, 14, 1.10, "Public Transport"), new Edge(v1, 14, 1.10, "Public Transport"), new Edge(v2, 28, 2.90, "Public Transport"),
                new Edge(v3, 7, 0.81, "Public Transport"), new Edge(v5, 18, 1.50, "Public Transport"), new Edge(v6, 41, 2.90, "Public Transport"),
                new Edge(v7, 14, 0.91, "Public Transport"), new Edge(v8, 23, 1.30, "Public Transport"), new Edge(v0, 4, 5.40, "Taxi"), new Edge(v1, 6, 4.90, "Taxi"),
                new Edge(v2, 12, 12.30, "Taxi"), new Edge(v3, 7, 6.20, "Taxi"), new Edge(v5, 18, 17.10, "Taxi"), new Edge(v6, 29, 27.60, "Taxi"), new Edge(v7, 17, 12.40, "Taxi"),
                new Edge(v8, 15, 13.20, "Taxi"), new Edge(v0, 12, 0, "Foot"), new Edge(v1, 16, 0, "Foot"), new Edge(v2, 123, 0, "Foot"), new Edge(v3, 19, 0, "Foot"),
                new Edge(v5, 41, 0, "Foot"), new Edge(v6, 280, 0, "Foot"), new Edge(v7, 34, 0, "Foot"), new Edge(v8, 75, 0, "Foot")};

        v5.adjacencies = new Edge[]{new Edge(v0, 24, 1.40, "Public Transport"), new Edge(v1, 24, 1.50, "Public Transport"), new Edge(v2, 39, 2.00, "Public Transport"),
                new Edge(v3, 19, 1.34, "Public Transport"), new Edge(v4, 18, 1.50, "Public Transport"), new Edge(v6, 69, 3.70, "Public Transport"),
                new Edge(v7, 7, 0, "Public Transport"), new Edge(v8, 35, 2.70, "Public Transport"), new Edge(v0, 16, 12.20, "Taxi"), new Edge(v1, 22, 20, "Taxi"),
                new Edge(v2, 20, 19, "Taxi"), new Edge(v3, 18, 17.10, "Taxi"), new Edge(v4, 17, 16, "Taxi"), new Edge(v6, 26, 25, "Taxi"), new Edge(v7, 7, 5.90, "Taxi"),
                new Edge(v8, 25, 25.10, "Taxi"), new Edge(v0, 50, 0, "Foot"), new Edge(v1, 41, 0, "Foot"), new Edge(v2, 145, 0, "Foot"), new Edge(v3, 44, 0, "Foot"),
                new Edge(v4, 41, 0, "Foot"), new Edge(v6, 250, 0, "Foot"), new Edge(v7, 9, 0, "Foot"), new Edge(v8, 108, 0, "Foot")};

        v6.adjacencies = new Edge[]{new Edge(v0, 86, 4.70, "Public Transport"), new Edge(v1, 79, 5.10, "Public Transport"), new Edge(v2, 75, 4.50, "Public Transport"),
                new Edge(v3, 52, 3.10, "Public Transport"), new Edge(v4, 41, 2.90, "Public Transport"), new Edge(v5, 69, 3.70, "Public Transport"),
                new Edge(v7, 52, 3.65, "Public Transport"), new Edge(v8, 69, 3.0, "Public Transport"), new Edge(v0, 30, 22.70, "Taxi"), new Edge(v1, 30, 28.70, "Taxi"),
                new Edge(v2, 25, 23.00, "Taxi"), new Edge(v3, 27, 24.80, "Taxi"), new Edge(v4, 32, 30.40, "Taxi"), new Edge(v5, 23, 17.70, "Taxi"), new Edge(v7, 22, 21.80, "Taxi"),
                new Edge(v8, 35, 33.30, "Taxi"), new Edge(v0, 288, 0, "Foot"), new Edge(v1, 344, 0, "Foot"), new Edge(v2, 264, 0, "Foot"), new Edge(v3, 285, 0, "Foot"),
                new Edge(v4, 280, 0, "Foot"), new Edge(v5, 250, 0, "Foot"), new Edge(v7, 245, 0, "Foot"), new Edge(v8, 325, 0, "Foot")};

        v7.adjacencies = new Edge[]{new Edge(v0, 22, 2.00, "Public Transport"), new Edge(v1, 20, 2.10, "Public Transport"), new Edge(v2, 35, 2.00, "Public Transport"),
                new Edge(v3, 14, 0.91, "Public Transport"), new Edge(v4, 14, 0.91, "Public Transport"), new Edge(v5, 7, 0.00, "Public Transport"),
                new Edge(v6, 52, 3.65, "Public Transport"), new Edge(v8, 35, 1.10, "Public Transport"), new Edge(v0, 14, 14.20, "Taxi"), new Edge(v1, 17, 18, "Taxi"),
                new Edge(v2, 20, 18.70, "Taxi"), new Edge(v3, 16, 13.90, "Taxi"), new Edge(v4, 16, 17, "Taxi"), new Edge(v5, 8, 9, "Taxi"), new Edge(v6, 21, 20, "Taxi"),
                new Edge(v8, 25, 25.10, "Taxi"), new Edge(v0, 42, 0, "Foot"), new Edge(v1, 37, 0, "Foot"), new Edge(v2, 137, 0, "Foot"), new Edge(v3, 39, 0, "Foot"),
                new Edge(v4, 34, 0, "Foot"), new Edge(v5, 9, 0, "Foot"), new Edge(v6, 245, 0, "Foot"), new Edge(v8, 100, 0, "Foot")};

        v8.adjacencies = new Edge[]{new Edge(v0, 22, 2.10, "Public Transport"), new Edge(v1, 38, 2.20, "Public Transport"), new Edge(v2, 24, 1.20, "Public Transport"),
                new Edge(v3, 26, 1.70, "Public Transport"), new Edge(v4, 23, 1.30, "Public Transport"), new Edge(v5, 35, 2.70, "Public Transport"),
                new Edge(v6, 69, 3, "Public Transport"), new Edge(v7, 35, 1.10, "Public Transport"), new Edge(v0, 13, 11.70, "Taxi"), new Edge(v1, 15, 14.20, "Taxi"),
                new Edge(v2, 16, 14.40, "Taxi"), new Edge(v3, 21, 18.60, "Taxi"), new Edge(v4, 15, 13.20, "Taxi"), new Edge(v5, 22, 21.800, "Taxi"), new Edge(v6, 35, 33.40, "Taxi"),
                new Edge(v7, 20, 18.40, "Taxi"), new Edge(v0, 59, 0, "Foot"), new Edge(v1, 86, 0, "Foot"), new Edge(v2, 81, 0, "Foot"), new Edge(v3, 83, 0, "Foot"),
                new Edge(v4, 75, 0, "Foot"), new Edge(v5, 108, 0, "Foot"), new Edge(v6, 325, 0, "Foot"), new Edge(v7, 100, 0, "Foot")};

        Vertex[] vertices = {v0, v1, v2, v4, v5, v6, v7, v8};

        Permutations<Vertex> perm = new Permutations<Vertex>(vertices);
        int count = 0;
        while(perm.hasNext()){
            System.out.println(Arrays.toString(perm.next()));
            count++;
        }
        System.out.println("total: " + count);
    }
}
