#include<stdio.h>
#include<math.h>
#include<string.h>
//this program is to get a user input value of the base first and then
//get another user input as the value. Calculate the value and 
//print. If the input value is not acceptable, then print out the 
//error message.
//@author: Runnan Zhou
int main(){
	char in[7];
	//user input.
	int n;
	//the base input
	int sum=0;
	//sum value for output
	int i;
	//for iterating
	int k;
	//for iterating
	int status=0;
	//status of the program. If error then return 1;
	if (scanf("%d",&n)!=1){
		status=1;
		fprintf(stderr, "Bad base input\n");
		//when the input is not a integer, then print error message.
		return status;
	}else if (n<2 || n>36){
		status=1;
		fprintf(stderr, "Bad value for base.\n");
		//if the input is not a acceptable integer, then print error message.
		return status;
	}else{

		while (scanf("%6s",in)!=EOF){
			for (k=0;in[k]!='\0';k++){
				if (in[k]>='A'&&in[k]<='Z'){
					in[k]+=32;
				}// if the input is uppecase, turn it to the lower case.
			}
			sum=0;
			for (i=strlen(in)-1;i>=0;i--){
				//iterating from the most right number.

				if (in[i]>='a'&& in[i]<n+'a'-10){
					sum+=(in[i]-'a'+10)*pow(n,strlen(in)-i-1);
					//if it is a alphabetic, then calculate.
					

				}else if (in[i]>='0' && in[i]<='9'){
					sum+=(in[i]-'0')*pow(n,strlen(in)-i-1);
					//if it is a integer, then calculate.
					
				}else{
					status=1;
					fprintf(stderr, "Not a %d base number\n",n);
					// if the input is not a base number, then print the error message.
					return status;
				}
			}
			fprintf(stdout, "%d\n",sum);
			//print out the sum value.
			
		}
	}
	return status;
}