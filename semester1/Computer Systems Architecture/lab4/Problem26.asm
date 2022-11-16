;Given 2 dublucuvinte R and T. Compute the doubleword Q as follows:
;the bits 0-6 of Q are the same as the bits 10-16 of T
;the bits 7-24 of Q are the same as the bits 7-24 of (R XOR T).
;the bits 25-31 of Q are the same as the bits 5-11 of R.
bits 32 

global  start 

extern  exit 
import  exit msvcrt.dll
segment  data use32 class=data 
    R dd 01110111010101110111011101010111b
    T dd 10011011101111101001101110111110b  
    Q dd 0
segment  code use32 class=code ; code segment
start: 
    mov ebx,0
    mov eax,[T]
    and eax,00000000000000011111110000000000b; isolate the bits 10-16 of T
    mov cl,10
    ror eax,cl;rotating 10 position to the right
    or ebx,eax;putting the bits in the result 
    mov eax,[R]
    mov ecx,[T]
    xor eax,ecx; in eax will be the value of R XOR T
    and eax,00000001111111111111111110000000b;isolate the bits 7-24 of R XOR T
    or ebx,eax; putting the bits in the result
    mov eax,[R]
    and eax,00000000000000000000111111100000b
    mov cl,13
    rol eax,cl
    or ebx,eax
    mov [Q],ebx
     push dword 0 
     call [exit] 