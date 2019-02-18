// Author: Runnan Zhou
// This program is based on the user input and get the Prime
// number which is greater than the input. Give error message
// when the input is not acceptable.
#include<stdio.h>
int main(){
	int n;
	int isPrime=1;
	// judge whether it is Prime. 
	
	
	while (scanf("%d",&n)==1){
		if(n<1){
			fprintf(stderr, "Error: input value %d is not positive\n",n);
			return 1;
			// return when the input is not acceptable. To meet the
			// example program output.
		}else{
			if (n==2 ||n==3){
				printf("%d\n",n);
				return 0;
				// the special cases. 
			}
			for (int i=n;i<2*n;i++){
				isPrime=1;
				for (int k=2;k<n/2;k++){
					if (i%k==0){
						isPrime=0;
						// the case which is not prime.
					}
				}

					if (isPrime==1&&i>n){
						printf("%d\n",i);
						return 0;
						// the prime cases.
					}
				}
        
			
				
				
			
		}
	}
	if (scanf("%d",&n)==0){
		// if input is not acceptable. give the error message.
		fprintf(stderr, "Error: input is not an integer\n");
		return 1;
	}
	return 0;
}
