bits 32 
global start
extern exit; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll; exit is a function that ends the calling process. It is defined in msvcrt.dll
; our data is declared here (the variables needed by our program)
segment data use32 class=data
	sir  dd  127F5678h,0ABCDABCDh
	len equ ($-sir)/4;the length of the string (in dd)
    suma_impar dw  0;variabile used for holding the sum of the digits 
    result resd len
; our code starts here
segment code use32 class=code
    start:
    ; An array with doublewords containing packed data (4 bytes written as a single doubleword) is given. Write an asm program in order to obtain a new array of doublewords, where each doubleword will be composed by the rule: the sum of the bytes from an odd position will be written on the word from the odd position and the sum of the bytes from an even position will be written on the word from the even position. The bytes are considered to represent signed numbers, thus the extension of the sum on a word will be performed according to the signed arithmetic.
;Example:
;for the initial array:
;127F5678h, 0ABCDABCDh, ...
;The following should be obtained:
;006800F7h, 0FF56FF9Ah, ... 
mov eax,0
mov ebx,0
mov ecx,0
mov edx,0
	mov esi, sir;in eds:esi we will store the FAR address of the string "sir"
    mov edi,result
	cld;parse the string from left to right(DF=0).    
	mov ecx, len;we will parse the elements of the string in a loop with len iterations.
	repeta:
		lodsb;
		mov bl,al ; bl : 78
        lodsb
        mov dl,al ; dl :56
        lodsb; al: 7F
        mov dh,al  ; dh : 7F
        lodsb ; al:12
        mov ah,al
        mov al,dh  ; al:7F
        mov dh,ah ; dh:12
        
        add al,bl ; al: F7
        jnae carry
        add dl,dh   ; dl:68
        mov [suma_impar],al
        mov al,0
        STOSB;  result : 00
        mov al,dl
        STOSB ; result: 0068
        mov al,0
        STOSB ; result:006800
        mov al,[suma_impar]
        STOSB; result 006800F7
        jmp end_cond
        
      
		
		carry:
         
        
         add dl,dh   ; dl:68
         mov [suma_impar],al
         mov al,0FFh
        STOSB;  result : FF
        mov al,dl
        STOSB ; result: FF56
         mov al,0FFh
        STOSB ; result:FF56FF
        mov al,[suma_impar]
        STOSB; result FF56FF9A
         
         
     
        end_cond:
        
	loop repeta;if there are more elements (ecx>0) resume the loop.



	
sfarsit:;end the program
           
        push dword 0; push the parameter for exit onto the stack
        call [exit]; call exit to terminate the program
        