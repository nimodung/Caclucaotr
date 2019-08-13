/*
 * Calculator.c
 *
 * Created: 2019-04-17 오전 11:12:30
 * Author : Kim Hee Ram
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <stdio.h>

#include "Keypad.h"
#include "Uart.h"

int main(void)
{
	char long_key_flag = 0;
	int result = 0, number = 0;
	char opcode = 0;
	
	Keypad_init();
	UART0_init(9600);
	   
	while (1) 
	{
		if(long_key_flag)
		{
			if(Keyscan() != 'A')
			{
				_delay_ms(1); //잡음 없애기 위한 딜레이
				if(Keyscan() != 'A')
				{
					if(Key_trans(Keyscan()) < 10) //숫자키
					{
						TX0_char(Key_trans(Keyscan()) + '0');
						number = (number * 10) + Key_trans(Keyscan());
						
						long_key_flag = 0;
					}
					else //연산자
					{
						 //opcode = Key_trans(Keyscan());
						 
						 //연산자 수정 switch문
						switch(Key_trans(Keyscan()))
						{
							case '+' :
							case '-' :
							case '*' :
							case '/' :
								
 								if(opcode && (opcode != '='))
 								{
									TX0_char('\b'); //back space
								 }
								break;
							default: break;
							 
						}
						 
						TX0_char(Key_trans(Keyscan()));				 
						switch(Key_trans(Keyscan()))
						{
							case '+' : 
							case '-' :
							case '*' :
							case '/' : 
								if(!opcode)
								{
									result = number;
									number = 0;
								}
								opcode = Key_trans(Keyscan()); 
								break;
							
							case '=' :
								switch(opcode)
								{
									case '+' : result = result+number; break;
									case '-' : result = result-number; break;
									case '*' : result = result*number; break;
									case '/' : result = result/number; break;
									default: break;
								} 
								number = 0;
								printf("%d \n\n\r%d", result, result);
								opcode = Key_trans(Keyscan()); 
								break;
							
							case 'C' : 
								number = 0;
								opcode = 0; 
								result = 0;
								
								printf("\n\r"); 
								break;
								
							default: break;
						}
						long_key_flag = 0;
					 }
				 }
			 }
		 }
		 else
		 {
			 if(Keyscan() == 'A')
			 {
				  _delay_ms(1);
				 if(Keyscan() == 'A')
				 {
					 long_key_flag = 1;
				 }
			 }
		 }
    }
}

