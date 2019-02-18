//This program is to rotate the input with the specific input value.
//Give error message when occured. Design with the nested loop to 
//solve the problem.
//@Author:Runnan Zhou
#include<stdio.h>
#include<stdlib.h>
int main(){
	int n;
	int retval;
	int *sequence;
	int in;
	int rotation;
//specific value to deceide how the interger sequence will be rotated.
	int a;
	int status=0;
	retval = scanf("%d",&n);
	if (retval!=1){
//if the first input integer is not a integer.
		fprintf(stderr, "Error in inputing vector size\n");
		status=1;
		return status;
	}else{
		if (n<=0){
//if it is not positive
			fprintf(stderr, "Vector size is not positive\n");
			status=1;
			return status;
		}
		sequence=malloc(n*sizeof(int));
//allocate the memory to the sequence.
	}
	for (int k=0;k<n;k++){
		in=0;
		retval=scanf("%d",&in);
//input integer seqence. Make them into a array.
			sequence[k]=in;	
	}
	if (sequence[n-1]=='\0'){
//if the length is not legal.
		fprintf(stderr, "Error in inputing vector.\n");
		status=1;
		return status;
	}
	scanf("%d",&rotation);
//input the rotation value.
	if (rotation>0){
		for (int j=0;j<rotation;j++){
//rotate when the value is greater than 0;
			a=sequence[n-1];
			for (int i=n-2;i>=0;i--){
				sequence[i+1]=sequence[i];
			}
			sequence[0]=a;
		}
	}else{
		for (int j=0;j<-rotation;j++){
//rotate when the value is less than 0;
			a=sequence[0];
			for (int i=0;i<n-1;i++){
				sequence[i]=sequence[i+1];
			}
			sequence[n-1]=a;
		}
	}
	for (int i=0;i<n;i++){
//print the result per integer.
		fprintf(stdout, "%d ", sequence[i]);
	}
	fprintf(stdout, "\n");
	return status;
}