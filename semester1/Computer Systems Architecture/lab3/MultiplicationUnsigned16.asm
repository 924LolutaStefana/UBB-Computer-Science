;x/2+100*(a+b)-3/(c+d)+e*e; a,c-word; b,d-byte; e-doubleword; x-qword
;UNSIGNED
bits 32 
global  start 

extern  exit

import  exit msvcrt.dll
        
segment  data use32 class=data 
	a dw 300
    b db 9
    c dw 400
    d db 5
    e dd 500
    x dq 800
    
   
segment  code use32 class=code ; code segment
start: 
   
 
    mov ecx,0
    mov ebx,0
    mov eax,0
    mov edx,0
    mov al,[b]
    mov ah,0
    add ax,[a] ; ax->a+b
    mov bx,100
    mul bx; dx:ax-> 100*(a+b)
   
    mov cx,dx
    mov bx,ax ; cx:bx-> 100*(a+b)
    mov dl,[d]
    mov dh,0
    add dx,[c] ; dx-> c+d
    mov eax,3
    ;mov dx,0
    div dx ; dx:ax->3/(c+d)
    sub cx,dx
    sub bx,ax ; cx:bx-> 100*(a+b)-3/(c+d)
    mov eax,[d]
    mul eax; edx:eax->d*d
    add ecx,edx
    add ebx,eax;ecx:ebx-> 100*(a+b)-3/(c+d) + d*d
    mov eax,[x+0]
    mov edx,2
    div edx ; edx:eax-> x/2
    add eax,ebx
    add edx,ecx ;edx:eax-> x/2 +100*(a+b)-3/(c+d) + d*d
    push   dword 0 
	call   [exit] 