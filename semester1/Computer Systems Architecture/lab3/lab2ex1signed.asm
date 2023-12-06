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
    d dq 10


; our code starts here
segment code use32 class=code
    start:
        ; a - byte, b - word, c - double word, d - qword - Signed representation    1.(c+b+a)-(d+d)
        mov eax,0
        mov ebx,0
        mov ecx,0
        mov edx,0
        mov al,[a]
        cbw
        cwd; eax=a
        mov ebx,eax; ebx=a
        mov ax,[b]
        cwd ; eax=b
        clc; clearing carry flag
        adc eax,ebx; eax=b+a
        mov ebx,[c]
        clc
        adc eax,ebx ; eax=c+b+a
        push eax; storing in the stack for later
        mov eax, dword [d+0] 
        mov edx, dword [d+4] 

        clc ; clear Carry Flag (punem 0 in CF) 
        add eax, eax  ;
        adc edx, edx ; edx:eax=d+d
        
        mov ebx,eax
        mov ecx,edx  ;ecx:ebx=d+d
        
        pop eax; eax=c+b+a
        cdq
        clc ; clear Carry Flag (punem 0 in CF) 
        sub eax, ebx  ;
        sbb edx, ecx ; ; ecx:ebx=(c+b+a)-(d+d)
        
        
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
