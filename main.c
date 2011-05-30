#include <stdio.h>

int add(int num1, int num2)
{
	return num1 + num2;
}



int main()
{
	int x,y,selection,result;

	printf("Press   1 for addition operation\n\t2 for subtraction\n\t3 for multiplication\n\t4 for division\n\t5 for modulo operation\n");

	scanf("%d",&selection);
   
   printf("Please enter two integers:");
   
   scanf("%d %d",&x,&y);

	if(selection == 1){
         result=add(x,y);
			printf(("%d",result);
	}
	else if(selection == 2){
	//subtract func call
	}
	else if(selection == 3){
	//mult func call
	}
	else if(selection == 4){
	//div func call
	}
	else if(selection == 5){
	//modulo func call
	}

	return 0;
}

