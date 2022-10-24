bits 32 
global  start 
;a,b,c - byte, d - word
;[d-(a+b)*2]/c
extern  exit

import  exit msvcrt.dll
        
segment  data use32 class=data 
	a db 4
    b db 10
    c db 2
    d dw 300
    aux db 0
segment  code use32 class=code ; code segment
start: 
    mov ax,[a]
    add ax,[b]
    mov cx,2
    mul cx
    mov [aux],al
    mov ax,[aux]
    mov cx,[d]
    sub cx,ax
    mov ax,cx
    mov cx,[c]

  
    div cl
    
    
    push   dword 0 
	call   [exit] 