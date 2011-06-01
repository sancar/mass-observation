#include <stdio.h>
#include <math.h>

int add(int num1, int num2)
{
        return num1 + num2;
}
int subtract(int num1, int num2)
{
        return num1 - num2;
}
int modulo(int num1, int num2)
{
        return num1 % num2;
}

int multiplication(int num1,int num2)
{
        return num1*num2;
}

int power(int num1, int num2)
{
        return pow(num1,num2);
}

int division(int num1,int num2)
{
        return num1/num2;
}

int main()
{
        int x,y,selection,result;

        printf("Press   1 for addition operation\n\t2 for subtraction\n\t3 for multiplication\n\t4 for division\n\t5 for modulo\n\t6 for power operation\n");

        scanf("%d",&selection);

   printf("Please enter two integers:");

   scanf("%d %d",&x,&y);

        if(selection == 1){

                result=add(x,y);
                printf("%d\n",result);
        }
        else if(selection == 2){

                result=subtract(x,y);
                printf("%d\n",result);
        }
        else if(selection == 3){

               result = multiplication(x,y);
               printf("%d\n",result);
        }
        else if(selection == 4){
               if(y!=0)
               {
                   result=division(x,y);
                   printf("%d\n",result);
               }
        }
        else if(selection == 5){

                        if(y != 0)
                        {
                          result = modulo(x,y);
                          printf("%d\n",result);
                        }
        }
        else if(selection == 6){
            result = power(x,y);
            printf("%d\n",result);
        }

        return 0;
}
