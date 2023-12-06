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
    e dw 2
    f dw 1

; our code starts here
segment code use32 class=code
    start:
        ; a,b,c,d-byte, e,f,g,h-word    16.a*a-(e+f)
    
        mov al,[a]
        mul al  ; al:a*a
        mov ah,0; unsigned conversion
        mov bx,[e]
        add bx,[f]; bx=e+f
        sub ax,bx; ax= a*a-(e+f)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
