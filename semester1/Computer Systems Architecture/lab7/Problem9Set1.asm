
bits 32
global start        
;Read two numbers a and b (base 10) from the keyboard and calculate: (a+b)/(a-b). The quotient will be stored in a variable called ;"result" (defined in the data segment). The values are considered in signed representation.
; declaring extern functions used in the program
extern exit, printf, scanf            
import exit msvcrt.dll     
import printf msvcrt.dll     ; indicating to the assembler that the printf fct can be found in the msvcrt.dll library
import scanf msvcrt.dll      ; similar for scanf
                          
segment  data use32 class=data
	a resb 1       ; this is the variable where we store the number read from keyboard
    b resb 1
	
	format1  db "%d", 0  ; %d <=> a decimal number (base 10)
    format5 db "%d", 0
    format3 db "The result is %d ", 0 
    format2 db "a= ", 0 
    format4 db "b= ", 0 
    
    
    result db 0
segment  code use32 class=code
    start:
        push dword format2
        call [printf]
        add esp, 4;printing "a= "
        
        push dword a
        push dword format1
        call [scanf]
        add esp, 4*2; reading a
         
        push dword format4
        call [printf]
        add esp, 4;printing "b= "
        
       
        
        push dword b
        push dword format5
        call [scanf]
        add esp, 4*2; reading b
        
        mov al,[a]
        add al,byte[b]
       
        mov bl,[a]
        sub bl,[b]
        cbw
       
        idiv bl
        mov [result],al
        cbw
        
        cwd
        push eax
        push dword format3
        call [printf]
        add esp, 4*2
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program