// Author: Runnan Zhou
// this program is based on the user input and compute
// whether it is a factorial or not. give error message when
// input is not meeting the requirement.
#include<stdio.h>
// include stdio for the rest code.

int main(){
	// the main function

	int n;
	// input n
	int status=0;
	// the status value of the program. Whether 0 or 1.
	
	
	while(scanf("%d",&n)==1){
		int sum=1;
		int fac=1;
		if (n<1){
			fprintf(stderr,"Error: input value %d is not positive\n",n);
			status=1;
			// print to stderr when the input is less than 1.
			
		}else{
			
		while (sum<n){
			sum= sum * fac;
			fac++;

		}
		if (sum==n){
			if (n==1){
				printf("%d = %d!\n",n,fac);
			}else{
				fac-=1;
				printf("%d = %d!\n",n,fac);
			}
			// if sum is equal to n then it is a factorial.
			
		}
		if (sum>n){
			printf("%d not factorial\n",n );
			// not factorial
		}

	}
		}
	if (scanf("%d",&n)==0){
		// if it is not an integer, print to stderr
		fprintf(stderr,"Error: input is not an integer\n");
		status=1;
		return status;
	}
	return status;
	// return the status of the program.
}
