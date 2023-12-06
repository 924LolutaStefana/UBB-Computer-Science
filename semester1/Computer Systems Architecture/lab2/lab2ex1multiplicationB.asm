bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    a db 2
    b db 1
    c db 4
    patru db 4
  

; our code starts here
segment code use32 class=code
    start:
        ; a,b,c,d-byte, e,f,g,h-word    1.((a-b)*4)/c
    
        ; exit(0)
        mov al,[a]
        sub al,[b]
        mul byte[patru] ; in ax : (a-b)*4
        div byte[c]; al:((a-b)*4)/c
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
