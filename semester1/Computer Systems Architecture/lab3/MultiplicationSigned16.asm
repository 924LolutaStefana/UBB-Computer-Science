bits 32 
global  start 
;x/2+100*(a+b)-3/(c+d)+e*e; a,c-word; b,d-byte; e-doubleword; x-qword
;SIGNED
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
   
    mov al,[d]
    cbw
    add ax,[c]
    mov bx,ax ; bx=d+c
    mov ax,3
    mov dx,0
    idiv bx; dx:ax=3/(d+c)
    push dx
    push ax
    pop ecx; ecx=3/(d+c)
    mov eax,0
    mov edx,0
    mov al,[b]
    cbw
    add ax,[a] ; ax=a+b 
    mov bx,100
    imul bx ;dx:ax=100*(a+b)
    push dx
    push ax
    pop eax; eax=100*(a+b)
    sub eax,ecx  ;eax =100*(a+b)-3/(d+c)
    mov ecx,eax ;ecx =100*(a+b)-3/(d+c)
    mov eax,[x+0]
    mov edx,[x+4]
    mov ebx,2
    idiv ebx ; edx:eax =x/2
    mov ebx,0
    adc eax,ecx
    add edx,ebx ; edx:eax=x/2+100*(a+b)-3/(d+c)
    push edx
    push eax
    mov eax,[e]
    imul eax; edx:eax=e*e
    pop ecx
    pop ebx
    add edx,ebx
    add eax,ecx ;edx:eax=x/2+100*(a+b)-3/(d+c) + e*e
    
  
    
    
    
    
    
    
    
   
    
    
   
    
    push   dword 0 
	call   [exit] 