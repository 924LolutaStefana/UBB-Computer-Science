bits 32 
global  start 

extern  exit

import  exit msvcrt.dll
        
segment  data use32 class=data 
	result db 0
   
segment  code use32 class=code ; code segment
start: 
    mov ax,14
    mov cl,6
    div cl
    mov [result],al
    push   dword 0 
	call   [exit] 