// Author : Runnan Zhou
// This program is based on the input and sum the input value
// with the reversed input value. Give error message when the 
// input is not acceptable.
#include<stdio.h>
int main(){
	int n;
	int original;
	// the original is to keep the input value.
	int rev_num=0;
	// the reversed input.
	int sum;
	int status=0;
	// the status of the program.
	
	
	while(scanf("%d",&n)==1){
		if (n<1){
			fprintf(stderr, "Error: input value %d is not positive\n",n);
			status=1;
			// not positive number is not acceptable.

		}else{
			original=n;
			// keep the value of n.
			rev_num=0;
			while(n>0){				
				rev_num=rev_num*10+n%10;
				n=n/10;
			}
			// get the reverse number by /10 .
			sum=rev_num+original;
			printf("%d\n",sum);
			
		}
	}
	if (scanf("%d",&n)==0){
		fprintf(stderr,"Error: input is not an integer\n");
		status=1;
		// print to stderr when the input is not an integer.
	}
	return status;
}