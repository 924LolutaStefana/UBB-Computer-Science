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
    b db 1 
    c db 2
    d db 1

; our code starts here
segment code use32 class=code
    start:
        ; a,b,c,d - byte     1.c-(a+d)+(b+d)
        mov al,[a]
        mov bl,[d] 
        add al,bl  ; al:a+d
        mov cl,al    ; moving a+d in cl to use al for storing c
        mov al,[c] 
        sub al,cl ; al:c-(a+d)
        mov bl,[b]
        mov cl,[d]
        add bl,cl ; bl:b+d
        add al,bl; al:c-(a+d)+(b+d)
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
