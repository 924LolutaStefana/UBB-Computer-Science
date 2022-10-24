;a,b,c,d-byte, e,f,g,h-word
;29[[b*c-(e+f)]/(a+d)
bits 32 
global  start 

extern  exit

import  exit msvcrt.dll
        
segment  data use32 class=data 
	a db 4
    b db 6
    c db 2
    d db 8
    e dw 100
    f dw 200
    aux db 0
segment  code use32 class=code ; code segment
start: 
    
    mov ax,[b]
    mov cx,[c]
    mul cx
    mov [aux],al
    mov ax,[e]
    mov cx,[f]
    add cx,ax
    mov ax,[aux]
    sub ax,cx
    mov cx,[a]
    mov bx,[d]
    add cx,bx
    idiv cl
    
    push   dword 0 
	call   [exit] 