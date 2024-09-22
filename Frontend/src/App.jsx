import { useState } from 'react'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"

export default function Component() {
  const [matricula, setMatricula] = useState('')
  const [nome, setNome] = useState('')

  const handleSubmit = (e) => {
    e.preventDefault()
    // Aqui você pode adicionar a lógica para enviar os dados para o seu sistema de ponto
    console.log('Dados submetidos:', { matricula, nome })
    // Limpar os campos após o envio
    setMatricula('')
    setNome('')
  }

  return (
    <Card className="w-full max-w-md mx-auto">
      <CardHeader>
        <CardTitle>Registro de Ponto</CardTitle>
        <CardDescription>Insira sua matrícula e nome para registrar o ponto</CardDescription>
      </CardHeader>
      <form onSubmit={handleSubmit}>
        <CardContent className="space-y-4">
          <div className="space-y-2">
            <Label htmlFor="matricula">Matrícula</Label>
            <Input 
              id="matricula" 
              placeholder="Digite sua matrícula" 
              value={matricula}
              onChange={(e) => setMatricula(e.target.value)}
              required
            />
          </div>
          <div className="space-y-2">
            <Label htmlFor="nome">Nome do Funcionário</Label>
            <Input 
              id="nome" 
              placeholder="Digite seu nome completo" 
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              required
            />
          </div>
        </CardContent>
        <CardFooter>
          <Button type="submit" className="w-full">Registrar Ponto</Button>
        </CardFooter>
      </form>
    </Card>
  )
}