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
        ; a - byte, b - word, c - double word, d - qword - Signed representation    16.(d-a)-(a-c)-d
        mov al,[a]
        cbw
        cwde
        cdq ; edx:eax=a
        mov ebx, dword [d+0] 
        mov ecx, dword [d+4] 
        clc ; clear Carry Flag (punem 0 in CF) 
        sub ebx, eax  ; 
        sbb ecx, edx ; ecx:ebx= d-a
        mov al,[a]
        cbw
        cwde; eax=a
        sub eax,[c] ; eax=a-c
        cdq
        clc ; clear Carry Flag (punem 0 in CF) 
        sub ebx, eax  ; 
        sbb ecx, edx ; ecx:ebx= (d-a) - (a-c)
        
        mov eax, dword [d+0] 
        mov edx, dword [d+4] 
        clc ; clear Carry Flag (punem 0 in CF) 
        sub ebx, eax  ; 
        sbb ecx, edx ; ecx:ebx= (d-a) - (a-c) -d
       
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
