bits 32 
global  start 
;29.(d-a)+(b+b+c)
extern  exit

import  exit msvcrt.dll
        
segment  data use32 class=data 
	a dw 120
    b dw 200
    c dw 100
    d dw 140
   
segment  code use32 class=code ; code segment
start: 
    mov ax,[d]
    sub ax,[a]
    mov cx,[b]
    add cx,[b]
    add cx,[c]
    add ax,cx
    push   dword 0 
	call   [exit] 