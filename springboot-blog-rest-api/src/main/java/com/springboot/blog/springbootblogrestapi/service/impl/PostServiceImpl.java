package com.springboot.blog.springbootblogrestapi.service.impl;

import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.model.Category;
import com.springboot.blog.springbootblogrestapi.model.Post;
import com.springboot.blog.springbootblogrestapi.payload.PostDto;
import com.springboot.blog.springbootblogrestapi.payload.PostResponse;
import com.springboot.blog.springbootblogrestapi.repository.CategoryRepository;
import com.springboot.blog.springbootblogrestapi.repository.PostRepository;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;
    private ModelMapper modelMapper;

    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert postdto to post
        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(()->new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));


        Post pst=mapToEntity(postDto);
        pst.setCategory(category);
        Post newPost=postRepo.save(pst);

        //convert post to postdto
        PostDto newResponse=mapToDTO(newPost);

        return newResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts=postRepo.findAll(pageable);

        List<Post> listOfPosts=posts.getContent();

        List<PostDto> content=listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        
        return postResponse;

    }

    @Override
    public PostDto getPostById(Long id) {
        Post post=postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto post, long id) {
        Post pst=postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));

        Category category = categoryRepository.findById(post.getCategoryId())
                .orElseThrow(()->new ResourceNotFoundException("Category", "id", post.getCategoryId()));
        pst.setTittle(post.getTittle());
        pst.setDescription(post.getDescription());
        pst.setContent(post.getContent());
        pst.setCategory(category);
        Post updatedPost=postRepo.save(pst);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post pst=postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        postRepo.deleteById(id);
        return;
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));

        List<Post> posts = postRepo.findByCategoryId(categoryId);

        return posts.stream().map(post->mapToDTO(post)).collect(Collectors.toList());
    }

    private PostDto mapToDTO(Post post){
        PostDto postDto=modelMapper.map(post, PostDto.class);
//        postDto.setId(post.getId());
//        postDto.setTittle(post.getTittle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post=modelMapper.map(postDto, Post.class);
//        post.setTittle(postDto.getTittle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }


}
