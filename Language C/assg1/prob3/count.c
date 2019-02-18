//This program is simly count the times of the same input integer
//appears. Use nested loop to slove this problem. Give the error 
//message when the error cases occurs.
//@Author: Runnan Zhou
#include<stdio.h>
#include<stdlib.h>
//include the library to implement malloc.

int main(){
	int n;
	int retval;
	int *sequence;
	//initialize the int array sequence.
	int k=0;
	int in;
	int ignore=0;
	int count=0;
	int x;
	int y;
	int a;
	int status=0;

	retval=scanf("%d",&n);
	if (retval!=1){
	//Judge whether the input is a integer.
		fprintf(stderr, "Bad or no number entered\n");
		status=1;
		return status;
	}else{
		if (n<=0){
			fprintf(stderr, "The size is not a positive number\n");
	//Judge whether the input is a positive integer.
			status=1;
			return status;
		}
		sequence = malloc(n*sizeof(int));
	//allocate the memory dynamically to the sequence.
	}
	for (k=0;k<n;k++){
		in =0;
		retval=scanf("%d",&in);
		
			sequence[k]=in;
	//Load the integer into the right position of arrry.
	}
	
	if (sequence[n-1]=='\0'){
		fprintf(stderr, "Input was not %d integers\n",n);
		status=1;
		return status;
	//Judge whether the input length is less than the n.
	}

	for (x=0;x<n;x++){
		for (y=x+1;y<n;y++){
			if (sequence[x]>sequence[y]){
				a=sequence[x];
				sequence[x]=sequence[y];
				sequence[y]=a;
			}
		}
	//sort the array to a from small to big sequence.
	}
	

	for (int i=0;i<n;i++){
		count=0;
		ignore=0;
		for (int h=0;h<i;h++){
				if (sequence[h]==sequence[i]){
					ignore =1;;
	//if left side has the same variable,means we just finished this

				}
			}
		for (int j=0;j<n;j++){
			
			
			if (ignore==0){
				if (sequence[j]==sequence[i]){
					count+=1;
	//find the same name variable and count add one.				
				}
			}

		}if (ignore==0){
			printf("%d %d\n", sequence[i], count);
	}
	//if it is the first time to do it. print the message.

	}
	return status;
}
