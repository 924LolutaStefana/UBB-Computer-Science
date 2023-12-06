bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    s1  db  'a','c','g','i'
    len_s1 equ ($-s1);the length of the string 
    s2 db 'b','d','e','f','h'
    len_s2 equ ($-s2);the length of the string 
    refjhb dw 0
    aux resb len_s1 + len_s2
    result resb len_s1 +len_s2
    

; our code starts here
segment code use32 class=code
    start:
        ; Being given two alphabetical ordered strings of characters, s1 and s2, build using merge sort the ordered string of bytes that contain all characters from s1 and s2.
        mov esi, s1;in eds:esi we will store the FAR address of the string "sir"
        mov edi, aux
        cld;parse the string from left to right(DF=0).    
        mov ecx, len_s1;we will parse the elements of the string in a loop with len iterations.
        repeta:
		lodsb; al:'a'
        stosb
           
	loop repeta;if there are more elements (ecx>0) resume the loop.
    
     mov esi, s2;in eds:esi we will store the FAR address of the string "sir"
        cld;parse the string from left to right(DF=0).    
        mov ecx, len_s2;we will parse the elements of the string in a loop with len iterations.
        repeta2:
		lodsb; 
        stosb
           
	loop repeta2
    
    mov esi, aux
    mov edi,result
   
    cld
    mov ecx,len_s2
    add ecx,len_s1
    mov edx,ecx
      repeta3:
		lodsb;
        mov bl,al
        
        al_doilea_for:
        lodsb
        cmp bl,al
        jg swap
        
        jmp end_cond
        
        swap:
        mov ah,al
        mov al,bl
        mov bl,ah
        
        stosb 
        dec edx
        
        cmp edx,0
        jG al_doilea_for
      
        
       
        end_cond:
        mov edx,ecx
        
	loop repeta3
    



		
sfarsit:;end the program
           
     
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
