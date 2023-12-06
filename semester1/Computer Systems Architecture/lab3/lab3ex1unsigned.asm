bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db 1
    b dw 2
    c dd 7
    d dq 1
    r resq 1; result

; our code starts here
segment code use32 class=code
    start:
        ; a - byte, b - word, c - double word, d - qword - Unsigned representation      1.c-(a+d)+(b+d)
        
        mov al,[a]; in this moment edx:eax = a
        mov AH,0 
        mov DX,0  
        push dx
        push ax
        pop eax
        mov EDX,0
        mov ebx, dword [d+0] 
	    mov ecx, dword [d+4] ; storing qword d ; ecx:ebx= d
        add eax, ebx  ; eax=  eax+ebx 
        add edx, ecx ; edx =  edx+ecx ; edx:eax= a+d
        mov ebx,eax
        mov ecx,edx; ecx:ebx= a+d
        mov eax,[c]; edx:eax= c 
        mov EDX,0
        sub eax, ebx  ; eax=  eax+ebx 
        sub edx, ecx ;edx =  edx+ecx; edx:eax=c-(a+d)
        push edx
        push eax ; pushing them on the stack for later
        mov bx,[b]; ecx:ebx=b
        mov CX,0  
        push cx
        push bx
        pop ebx
        mov ECX,0
        mov eax, dword [d+0] 
	    mov edx, dword [d+4]; storing qword d ; ecx:ebx= d
        add eax, ebx  ; eax=  eax+ebx 
        add edx, ecx ; edx:eax= b+d
        pop ebx
        pop ecx; ecx:ebx= c-(a+d)
        add eax, ebx  ; eax=  eax+ebx 
        add edx, ecx; edx:eax=c-(a+d)+(b+d)
        mov dword [r+0], eax 
        mov dword [r+4], edx ; r=edx:eax=c-(a+d)+(b+d)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
