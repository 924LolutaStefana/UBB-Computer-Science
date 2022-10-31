bits 32 
global  start 
;a - byte, b - word, c - double word, d - qword - Unsigned representation
;c-a-(b+a)+c
extern  exit

import  exit msvcrt.dll
        
segment  data use32 class=data 
	a db 4
    b dw 300
    c dd 500
    
   
segment  code use32 class=code ; code segment
start: 
    mov al,[a]
    mov ah,0
    mov bx,[b]
    add ax,bx
    mov bx,ax; avem valoarea in bx a lui b+a
    mov eax,0
    mov eax,[c]
    mov ecx,0
    mov cl,[a]
    sub eax,ecx; in eax va fi c-a
    mov ecx,0
    mov cx,bx
    sub eax,ecx
    mov ecx,0
    mov ecx,[c]
    add eax,ecx
    push   dword 0 
	call   [exit] 