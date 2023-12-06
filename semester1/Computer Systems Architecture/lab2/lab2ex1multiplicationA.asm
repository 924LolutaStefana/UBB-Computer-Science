bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db 3
    b db 2
    c db 1
    d dw 5
    doi db 2
; our code starts here
segment code use32 class=code
    start:
        ; a,b,c - byte, d - word    1.((a+b-c)*2 + d-5)*d
        mov al,[a]
        add al,[b]
        sub al,[c]
        mul byte[doi] ; in Ax: (a+b-c)*2
        mov bx,[d]
        add ax,bx
        sub ax,5
        mul bx ;dx:ax : ((a+b-c)*2 + d-5)*d
        
        
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
