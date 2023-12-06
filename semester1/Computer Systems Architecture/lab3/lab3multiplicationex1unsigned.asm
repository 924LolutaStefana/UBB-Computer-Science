bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db 2
    b dd 2
    c dq 7
    


; our code starts here
segment code use32 class=code
    start:
        ; 1.c+(a*a-b+7)/(2+a), a-byte; b-doubleword; c-qword   UNSIGNED
        mov eax,0
        mov ebx,0
        mov ecx,0
        mov edx,0
        mov al,[a]
        mul byte[a] ; ax=a*a
        mov dx,0
        push dx
        push ax
        pop eax ; eax=a*a
        sub eax,[b]
        add eax,7; eax=a*a-b+7
        push eax
        pop ax
        pop dx; dx:ax=a*a-b+7
        mov bl,[a]
        add bl,2
        mov bh,0
        div bx; ax=(a*a-b+7)/(2+a)
        mov ebx, dword [c+0] 
        mov ecx, dword [c+4] 
        mov dx,0
        push dx
        push ax
        pop eax
        mov edx,0
        add ebx, eax  ; 
        adc ecx, edx; ecx:ebx= c+(a*a-b+7)/(2+a)

        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
