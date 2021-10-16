package br.com.basis.sgt;


import br.com.basis.sgt.domain.Responsavel;
import br.com.basis.sgt.repository.ResponsavelRepository;
import br.com.basis.sgt.service.dto.ResponsavelDTO;
import br.com.basis.sgt.service.mapper.ResponsavelMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
public class ResponsavelResourceIT {

    public static final MediaType APPLICATION_JSON_UTF8 =  new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);


    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .build();
    }

    @Autowired
    public void setWebApplicationContext(WebApplicationContext pwebApplicationContext) {
        webApplicationContext = pwebApplicationContext;
    }

    protected MockMvc getMockMvc () {
        return mockMvc;
    }


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);

    }

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private ResponsavelMapper responsavelMapper;

    @BeforeEach
    private void limparBanco(){
        responsavelRepository.deleteAll();
    }

    private Long salvarResponsavel(ResponsavelDTO responsavelDTO){
        Responsavel save = responsavelRepository.save(responsavelMapper.toEntity(responsavelDTO));
        return  save.getId();
    }


    @Test
    public void criarResponsavel() throws Exception {
        ResponsavelDTO responsavelDTO = getResponsavelDTO();

        getMockMvc().perform(
                post("/api/responsaveis")
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(responsavelDTO))
        ).andExpect(status().isOk());
    }


    @Test
    public void obterPorIdcomSucesso() throws Exception{
        Long idResponsavel = salvarResponsavel(getResponsavelDTO());

            getMockMvc().perform(
                    get("/api/responsaveis/" + idResponsavel)
                        .contentType(APPLICATION_JSON_UTF8)
            ).andExpect(status().isOk());
    }



    @Test
    public void obterPorIdcomErro() throws Exception {
        salvarResponsavel(getResponsavelDTO());
        getMockMvc().perform(
                get("/api/responsaveis/" + Long.MAX_VALUE )
                 .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isNotFound());
    }



    private ResponsavelDTO getResponsavelDTO(){
        ResponsavelDTO responsavelDTO = new ResponsavelDTO();
        responsavelDTO.setNome("Nome 1");
        responsavelDTO.setSobre_nome("Sobre_nome 2");
        return  responsavelDTO;
    }



}
