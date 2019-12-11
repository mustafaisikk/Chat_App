/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.util.Random;

/**
 *
 * @author mustafa
 */
public class Encryption {
    
    public static char[] alfabe = {'A','B','C','Ç','D','E','F','G','Ğ','H','I','İ','J','K','L','M','N','O','Ö','P','R','S','Ş','T','U','Ü','V','Y','Z'};
    public static char[][] Polybius = {{'A','B','C','D','E'},
                                       {'F','G','Ğ','H','I'},
                                       {'J','K','L','M','N'},
                                       {'O','P','R','S','Ş'},
                                       {'T','U','V','Y','Z'}};
    
    
    ////////////////////////////////////// COLUMNNN //////////////////////////////////////
    public String EncryptColumn(String temp){
        char[][] columnchar;
        char[] tempchar;
        int sqrt,index=0,index2=0;
        String Output="";
        
        temp = temp.toUpperCase();
        temp = temp.replace(" ", "");
        tempchar = temp.toCharArray();
        sqrt = tempchar.length;
        sqrt = (int) Math.sqrt(sqrt)+1;
        columnchar = new char[sqrt][sqrt];
        for(int i = 0;i<sqrt ; i++){
            for(int j = 0;j<sqrt;j++){
                if(index<tempchar.length){
                    columnchar[i][j] = tempchar[index]; 
                    index++;
                }
                else
                    columnchar[i][j] =randomchar(); 

            }
        }
        
        index2 = 0;
        for(int k = 0; k<sqrt ; k++ ){
            
            for(int i = 0;i<sqrt ; i++){
                Output += columnchar[i][index2];
            }
            
            index2++;
        }
        
        return Output;
    }
    
    public String DecryptionColumn(String input){
        
        char[][] columnchar2;
        char[] tempchar2;
        int index=0,index3=0,sqrt2;
        String Output2="";
        
        tempchar2 = input.toCharArray();
        
        sqrt2 = (int)Math.sqrt(tempchar2.length);
        
        columnchar2 = new char[sqrt2][sqrt2];
        index = 0;
        
        for(int i = 0;i<sqrt2 ; i++){
            for(int j = 0;j<sqrt2;j++){
                if(index3<tempchar2.length){
                    columnchar2[j][index]=tempchar2[index3];
                    index3++;
                }else
                    columnchar2[j][index] = randomchar();
            }
            index++;
        }
        
        for(int i = 0; i<sqrt2 ; i++ ){
            
            for(int j = 0;j<sqrt2 ; j++){
                Output2 += columnchar2[i][j];
            }
        }
        
        return Output2;
    }  
    
    public static char randomchar(){
        Random random = new Random();
        int temp = random.nextInt(alfabe.length);
        
        return alfabe[temp];
    }
    
    ////////////////////////////////////// COLUMNNN //////////////////////////////////////
    
    
    
    
    /////////////////////// ---- CESAR ----- ////////////////////
    public String EncryptCesar(String value,int index){
        String Sifreli="";
        int temp;



        String[] metin2,metin3;
        value = value.toUpperCase();
        metin2 = value.split(" ");

        for(String var:metin2){
            for(char var2:var.toCharArray()){
                temp = SearchtoArray(var2);
                if((temp + index) >= alfabe.length){
                    Sifreli += alfabe[(temp+index-alfabe.length)];
                }
                else{
                    Sifreli += alfabe[(temp+index)];
                }
            }
        }
        
        return Sifreli;   
    }
    
    public String DecryptionCesar(String value,int input){
        String cozulmus="";
        int temp;


        for(char var:value.toCharArray()){
            temp = SearchtoArray(var);
            if((temp-input)<0){
                cozulmus += alfabe[(alfabe.length-(input-temp))]; 
            }else{
                cozulmus += alfabe[(temp-input)];
            }
        }
        
        return cozulmus;
    }
    
    public static int SearchtoArray(char c){
        
        int sonuc=0;
        
        for(char tempc : alfabe){
            if(tempc == c){
                break;
            }
            sonuc++;
        }
            
        return sonuc;
    }
    
    /////////////////////// ---- CESAR ----- ////////////////////

    
    
    
    /////////////////////// ---- Polybius ----- ////////////////////
    
    public String EncryptPolybius(String temp){
        
        String Output = "";
        char[] temp2;
        
        temp = temp.trim().replace(" ","");
        temp = temp.toUpperCase();
        
        temp2 = temp.toCharArray();
        
        for(int index = 0; index < temp2.length ; index++){
            Output += SearchtoArrayPolybius(temp2[index]);
        }
        
        return Output;
    }
    
    public String DecryptionPolybius(String temp){
    
        String Output = "";
        
        String [] temp2;
        int index;
        
        temp2 = temp.split(" ");
        
        int[][] array = new int[temp2.length][2];

        for(int i = 0;i<temp2.length;i++){
            index = 0;
            for(char var2 : temp2[i].toCharArray()){
                array[i][index] = Integer.valueOf(Character.toString(var2));
                index++;
            }        
        }
        
        for(int i = 0;i<temp2.length;i++){
            Output += ""+Polybius[(array[i][0] - 1)][(array[i][1] - 1)];
        }
        
        return Output;
    }
    
    public static String SearchtoArrayPolybius(char c){
        
        switch (c) {
            case 'Ç':
                return "13 ";
            case 'İ':
                return "25 ";
            case 'Ö':
                return "41 ";
            case 'Ü':
                return "52 ";
        }
        
        for(int i = 0 ; i<5 ; i++){
            for(int j = 0 ; j<5 ; j++){
                if(c == Polybius[i][j]){
                    return ""+(i+1)+(j+1)+" ";
                }
            }
        }
        return "";
    }

    /////////////////////// ---- Polybius ----- ////////////////////    
    
    
    
    /////////////////////// ---- VİGENERE ----- ////////////////////    

    public String EncryptVigenere(String temp,String temp2){
        
        char[][] deneme = new char[29][29];
        deneme = CreateVigenere();
        String Output = "";
        char[] CATemp,CATemp2;
        int count = 0;
        
        temp = temp.toUpperCase();
        temp = temp.replace(" ", "");
        temp2 = temp2.toUpperCase();
        temp2 = temp2.replace(" ", "");
        CATemp = temp.toCharArray();
        CATemp2 = temp2.toCharArray();
        
        for(int i = 0 ; i<CATemp.length;i++){

            if(count<temp2.length()){
                Output += deneme[SearchtoArray(CATemp2[i])][SearchtoArray(CATemp[i])];
                count++;
            }
            else 
                Output+=randomchar();
        }
        
        return Output;
    }
    
    public String DecryptionVigenere(String temp,String temp2){
        
        char[][] deneme = new char[29][29];
        deneme = CreateVigenere();
        String Output = "";
        char[] CATemp,CATemp2;
        int count=0;
        
        temp2 = temp2.toUpperCase();
        temp2 = temp2.replace(" ", "");
        CATemp = temp.toCharArray();
        CATemp2 = temp2.toCharArray();
        
        for(int j = 0; j<CATemp.length;j++){
            if(count < CATemp2.length){
                for(int i = 0 ; i<29;i++){
                    if((deneme[SearchtoArray(CATemp2[j])][i]) == CATemp[j]){
                        Output += alfabe[i];
                    }
                }
                count++;
            }
            else
                Output += randomchar();
        }
        
        
        return Output;
    }
    
    public static char[][] CreateVigenere(){
        
        char[][] deneme = new char[29][29];
        int temp = 0;
        
        for(int i = 0; i<29 ; i++){
            
            for(int j =0;j<29 ; j++ ){
                
                deneme[i][j] = alfabe[temp];
                temp++;
                if(temp == 29)
                    temp = 0;
            } 
            temp++;
            
        }
        
        return deneme;
    }
    
    /////////////////////// ---- VİGENERE ----- ////////////////////
    
    

    /////////////////////// ---- CİT ----- ////////////////////
    
    public String EncryptCIT(String input){
    
        String [] array;
        char[] chartemparray;
        String tek="",cift="";
        int temp=0,temp2=0;
        
        input = input.toUpperCase();
        
        for(char var:input.toCharArray()){
            if(temp == 0){
                tek += var;
                temp = 1;
            }
            else{
                cift += var;   
                temp = 0;
            }
        }
        // hsynmr es üei etnye
        return tek + cift;
        
    }
  
    public String DecryptionCIT(String input){
    
        int TekSay = 0,CiftSay = 0;
        char[] Chartek,Charcift;
        String tek="",cift="",Output="";
        
        if(input.length() %2 == 0){
            tek = input.substring(0,(input.length()/2));
            cift = input.substring((input.length()/2),input.length());
        }
        else{
            tek = input.substring(0,(input.length()/2)+1);
            cift = input.substring((input.length()/2)+1,input.length());
        }
        
        Chartek = tek.toCharArray();
        Charcift = cift.toCharArray();
        
        
        for(int i = 0;i<input.length();i++){
            if(i%2 == 0){
                Output +=Chartek[TekSay];
                TekSay++;
            }else{
                Output +=Charcift[CiftSay];
                CiftSay++;
            }
        }
        return Output;
    }
    
    
    /////////////////////// ---- CİT ----- ////////////////////


    
}
