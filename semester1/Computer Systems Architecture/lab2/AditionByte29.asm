bits 32 
global  start 
;29.(b+c)+(a+b-d)
extern  exit

import  exit msvcrt.dll
        
segment  data use32 class=data 
	a db 4
    b db 4
    c db 4
    d db 4
   
segment  code use32 class=code ; code segment
start: 
    mov ax,[b]
    add ax,[c]
    mov cx,[a]
    add cx,[b]
    sub cx,[d]
    add al,cl
    push   dword 0 
	call   [exit] 