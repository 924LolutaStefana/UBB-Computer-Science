bits 32 
global  start 
;a - byte, b - word, c - double word, d - qword - Signed representation
;(d-a)-(a-c)-d
extern  exit

import  exit msvcrt.dll
        
segment  data use32 class=data 
	a db 4
    b dw 300
    c dd 500
    d dq 700
    
   
segment  code use32 class=code ; code segment
start: 
    mov al,[a]
    mov ebx,[d+0]
    mov ecx,[d+4]
    cbw
    cwde
    sub ebx,eax
    sbb ecx,0 ; in ebx:ecx vom avea d-a
    mov al,[a]
    mov edx,[c]
    cbw
    cwde
    sub edx,eax ; in edx vom avea a-c
    sub ebx,eax
    sbb ecx,0 ; in ebx:ecx vom avea (d-a)- (a-c)
    mov eax,[d+0]
    mov edx,[d+4]
    sub ebx,eax
    sub ecx,edx;  in ebx:ecx vom avea (d-a)- (a-c) -d
    
    
   
    
    push   dword 0 
	call   [exit] 