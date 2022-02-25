/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez SaraEcheverria 
 * FileName: Sorts
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     4/03/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 23/02/2022
        - Ultima modificacion:

    Clase que tendra todos los sorts y permitira ordenar los elementos genericos
 */

public class Sorts<T> {

    private IComparator comparator;

    /*****************************************************************
     * Constructor que requiere el comparador
     * @param comparator
     */
    public Sorts(IComparator comparator){
        this.comparator = comparator;
    }
    //****************************************************************

    /*****************************************************************
     * GnomeSort: metodo que realiza un ordenamiento bajo el algoritmo Gnome
     * @param array
     */
    public void GnomeSort(T[] array){
        int i = 1;
        while (i < array.length - 1){ //Recorrer el arreglo
            if (comparator.Compare(array[i - 1], array[i]) < 0 ) // Comparar si el elemento anterior es menor que el actual
                i++; // Si el anterior es menor, se pasa a evaluar el siguiente elemento
            else{ // Los elementos no estan ordenados

                //swap
                T temp = array[i - 1];
                array[i - 1] = array[i];
                array[i] = temp;

                i--; //se reduce el numero de elemento que se evalua, para asa seguir evaluando si este elemento puede ser menor que otros anteriores a este 
                if (i == 0) //Si se llega a este punto (y sigue el ciclo activo), es un menor relativo. 
                    i = 1; // Se evaluara el siguiente elemento.
            }
        }

    }
    //****************************************************************

    /*****************************************************************
     * Quick Sort: metodo que realiza un ordenamiento bajo el algoritmo de un sort recursivo
     * @param array
     * @param size
     */
    public void QuickSort(T[] array, int inf, int sup){
        if (inf >= sup) //Caso trivial
            return ;
        else{ //Salto de fe
            int i = inf - 1, j = sup;
            boolean flag = true;
            T elem_div = array[sup] /* elemento pivote */ , temp;

            while(flag){
                while(comparator.Compare(array[++i], elem_div) < 0); // Compara que elementos son menores que el pivote
                while((comparator.Compare(array[--j], elem_div) > 0) && (j < inf)){ // Compara que elementos son mayores que el pivote
                    if (i < j){
                        //swap
                        temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                    else
                        flag = false;
                }
            }
            //Proceso para dividir mitades
            temp = array[i];
            array[i] = array[sup];
            array[sup] = temp;

            //--- RECURSION ---
            QuickSort(array, inf, i-1);
            QuickSort(array, i + 1, sup);
        }
    }
    //*****************************************************************

    /******************************************************************
     * Radix Sort: metodo que realiza un ordenamiento bajo el algoritmo de burbuja
     * @param array
     */
    public void RadixSort(T[] array){
        T max = array[0];
        int m;
        for(int i =1; i<array.length;i++){
            if(comparator.Compare(array[i],max) > 0){
                max = array[i];
            }
        }
        for(int exp = 1; max/exp > 0; exp *= 10){

        }
    }
    //*****************************************************************

    /******************************************************************
     * Bubble Sort: metodo que realiza un ordenamiento bajo el algoritmo de burbuja
     * @param array
     */
    public void BubbleSort(T[] array){
        for (int i = array.length; i < 1; i--) // Ciclo para determinar los elementos a los que se va a evaluar
            for (int j = 0; j < i -1; j++) // Ciclo que indica el elemento actual que se comparara
                if(comparator.Compare(array[j], array[j + 1]) > 0){ // Evalua si el elemento actual es mayor que el siguiente, de serlo, los intercambia de posicion
                    //swap
                    T temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
    }
    //*****************************************************************

    /******************************************************************
     * Merge Sort: metodo que realiza un ordenamiento bajo el algoritmo de Merge
     * @param array
     * @param size
     */
    public void MergeSort(T[] array, int inicio, int fin){
        // A
        if (inicio < fin)
        {
            // Encontrar el punto medio
            int mid = (inicio + fin) / 2;

            MergeSort(array, inicio, mid); // Sort extremo 1
            MergeSort(array, mid + 1, fin);  // Sort extremo 2

            // Merge ambos extremos
            merge(array, inicio, mid, fin);
        }

    }

    // metodo privado que hace la funcion de merge a las mitades del array obtenidas en sort
    private void merge(T[] array, int inicio, int mid, int fin)
    {
        T[] LArray  = (T[]) new Comparable[mid - inicio + 1];
        T[] RArray = (T[]) new Comparable[fin - mid];

        // Relleno extremo 1
        for (int i = 0; i < LArray.length; ++i)
            LArray[i] = array[inicio + i];

        // Relleno extremo 2
        for (int i = 0; i < RArray.length; ++i)
            RArray[i] = array[mid+ 1 + i];

        /* Funcion merge a los subgrupos temporales */

        // Indices iniciales
        int LIndex = 0, RIndex = 0;

        // Indice donde agregar subgrupos al array inicial
        int currentIndex = inicio;

        // Comparar cada indice de los subgrupos, se agrega el valor mas bajo
        while (LIndex < LArray.length && RIndex < RArray.length)
        {
            if(comparator.Compare(LArray[LIndex], RArray[RIndex]) <= 0){
                array[currentIndex] = LArray[LIndex];
                LIndex++;
            }
            else
            {
                array[currentIndex] = RArray[RIndex];
                RIndex++;
            }
            currentIndex++;
        }

        // Elementos restantes/sobrantes subgrupo 1
        while (LIndex < LArray.length) array[currentIndex++] = LArray[LIndex++];

        // Elementos restantes/sobrantes subgrupo 2
        while (RIndex < RArray.length) array[currentIndex++] = RArray[RIndex++];
    }
}
