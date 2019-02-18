#include<stdio.h>
#include <string.h>
#include <ctype.h>
//this program is to print the anagrams of the first enter word.
//since every first word is the anagram of itself. so print the first
//word whatever case it is. 
//author@:runnan zhou
int main(){
	char str [65]; //Assume the length of the string will not exceed 64. 
	char fstr[65];
	char original[65];//put the original string into this.
	char first_str[65];
	int  i;
	int status=0; //status of the program. Error will be 1.
	int num1[26]={0}; //build a int array to store value.
	int equal=1;
	int k=0;
	int n;
	scanf("%64s",fstr);
	
	strcpy(first_str,fstr);//copy the value of the fstr to the first_string.
	for ( n=0;first_str[n]!='\0';n++){
		if (!isalpha(first_str[n])){
			status=1;
			fprintf(stderr, "Bad first String\n");
			//print error message when the input value is not acceptable.
			return status;
		}
		
		first_str[n]=tolower(first_str[n]); // turn the string to lower.
		
			num1[first_str[n]-'a']++; //add 1 when there is a alpha.
		
	}
	fprintf(stdout, "%s\n", fstr);
	
	while (scanf("%64s",str)!=EOF){ // iterating until the end of the input.
		int num2[26]={0};
		strcpy(original,str);
		for  (i=0;str[i]!='\0';i++){  // iterating as much as it could.
			if (!isalpha(str[i])){ //check whether there is no-alphabetic character.
				status=1;
				fprintf(stderr,"Bad Input ... non-alphabetical character\n"); //print the error message as required.
				
			}	
			str[i]=tolower(str[i]); //turn the string to lower.
			num2[str[i]-'a']++;//add 1 when there is a alpha.
			


		}
		

		equal=1;
		for (k=0;k<26;k++){
			if (num1[k]!=num2[k]){
				equal=0; //compare the 2 arrays. Equal when there
				//is not a such condition.
			}
		 
		}
		if (equal==1){
			fprintf(stdout, "%s\n",original);
			//print the original string out.
		}
		

		
		

	}
	return status;
}