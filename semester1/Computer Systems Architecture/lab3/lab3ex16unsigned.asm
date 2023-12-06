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
    b dw 1
    c dd 7

; our code starts here
segment code use32 class=code
    start:
        ; a - byte, b - word, c - double word, d - qword - Unsigned representation      16.c-a-(b+a)+c
    
        mov eax,[c]
        mov bl,[a]
        mov BH,0 
        mov CX,0 
        push cx
        push bx
        pop ebx
   
        sub eax,ebx; eax:c-a
        mov bx,[b]
        mov cl,[a]
        mov CH,0 
        add bx,cx; bx:b+a
        mov CX,0  
        push cx
        push bx
        pop ebx
        sub eax,ebx; eax: c-a-(b+a)
        mov ebx,[c]
        add eax,ebx; eax:c-a-(b+a)+c
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
